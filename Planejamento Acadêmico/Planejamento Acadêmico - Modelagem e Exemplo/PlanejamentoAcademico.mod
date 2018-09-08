#conjuntos
set DISCI; 	# DISCI - D
set SALA; 	# SALA - S
set PROF; 	# PROF - P
set COMBO; 	# COMBO - C
set HORARIO; 	# HORARIO - H
set SEMESTRE; # SEMESTRE - E
set CURSO; # CURSOS - R
set TURNO; # TURNOS - T

param MINHp {PROF} >0;
param MAXHp {PROF} >0;
param Z {PROF,DISCI,COMBO, TURNO} >= 0;
param Nc {DISCI} > 0;
param Sem{DISCI, SEMESTRE, CURSO} >= 0;
param Tsalas integer;
param Cap{SALA} > 0;
param TamTurma{DISCI} > 0;
param Sob{COMBO, COMBO} >= 0; 
param Sobh{HORARIO, HORARIO} >= 0;
param Comp{COMBO, DISCI} >= 0;
param CompHC{COMBO, HORARIO}>=0;
param CompTH{TURNO, HORARIO} >=0;

var X {PROF, DISCI, COMBO, HORARIO, SALA} binary; 

#FUNÇÃO OBJETIVO 
maximize f:sum {p in PROF, d in  DISCI, c in COMBO, t in TURNO, h in HORARIO, s in SALA} X[p,d,c,h,s] * Z[p,d,c,t];

#RESTRIÇOES 
# Cada professor tem uma carga horária mínima
subject to MinimoHorario {p in PROF}:
sum {d in DISCI, s in SALA, c in COMBO, h in HORARIO} X[p,d,c,h,s] * Nc[d] >= MINHp[p];

# Cada professor tem uma carga horária máxima
subject to MaximoHorario {p in PROF}:
sum {d in DISCI, s in SALA, c in COMBO, h in HORARIO} X[p,d,c,h,s] * Nc[d] <= MAXHp[p];

# Um professor não pode estar em mais de uma sala no mesmo horário
subject to ProfessorSalaHorario {p in PROF, c in COMBO, h in HORARIO}:
sum {d in DISCI, s in SALA} X[p,d,c,h,s] <= 1;

# Não pode ter mais de uma disciplina do mesmo semestre e curso no mesmo horário
subject to SemestreHorario {e in SEMESTRE, c in COMBO, h in HORARIO, r in CURSO}:
sum {d in DISCI, p in PROF, s in SALA} X[p,d,c,h,s] * Sem[d,e,r] <= 1;

# Uma sala não pode ser alocada para mais de uma turma no mesmo horário
subject to SalaHorario {s in SALA, c in COMBO, h in HORARIO}:
sum {d in DISCI, p in PROF} X[p,d,c,h,s]  <= 1;

# Um professor não pode ser alocado para um dia que ele disse não poder
subject to ProfessorCombo {s in SALA, c in COMBO, h in HORARIO, d in DISCI, p in PROF}:
X[p,d,c,h,s] <= sum {t in TURNO} Z[p,d,c,t] * CompTH[t, h];

# Total de salas alocadas tem que ser menor que a quantidade de salas da universidade
subject to SalaHorarioTotal {c in COMBO, h in HORARIO}:
sum {d in DISCI, p in PROF, s in SALA} X[p,d,c,h,s]  <= Tsalas;

# Cada disciplina ofertada deve ser dada
subject to DisciplinaDada {d in DISCI}:
sum {c in COMBO, p in PROF, s in SALA, h in HORARIO} X[p,d,c,h,s]  = 1;

# Associa combos à horários
subject to HorarioCombo {h in HORARIO, c in COMBO, p in PROF, s in SALA, d in DISCI}:
X[p,d,c,h,s]  <= CompHC[c, h];

# Restringe combos sobrepostos
subject to ComboSobreposto {s in SALA, h in HORARIO, c1 in COMBO, c2 in COMBO} :
sum{p in PROF, d in DISCI}  (X[p,d,c1,h,s] + X[p,d,c2,h,s])  * Sob[c1,c2] <= 1;

# Restringe horários sobrepostos
subject to HorarioSobreposto {s in SALA, h1 in HORARIO, h2 in HORARIO, c1 in COMBO, c2 in COMBO} :
sum{p in PROF, d in DISCI}  (X[p,d,c1,h1,s] + X[p,d,c2,h2,s]) * Sobh[h1,h2] * Sob[c1,c2] <= 1;

# Restringe a alocação de salas para turmas que caibam
subject to CapacidadeMaxima{s in SALA, d in  DISCI, p in PROF, c in COMBO, h in HORARIO} :
X[p,d,c,h,s] * Cap[s] >= X[p,d,c,h,s] * TamTurma[d];
