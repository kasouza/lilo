
INPUT $1, $2, $3, $4;
PROGRAM 

sort $1 $2; -> [$1 $2]
nop $3 $4;  -> [$3 $4]
            -> [$1 $2 $3 $4]
| intersection $1 $2; intersection $3 $4  -> [$1+$2, $3+$4]
| sort $1; nop $2 -> [$1, $2]
| sort $1 -> [$1]
| sort $2; nop $1-> [$2, $1]



PROG dostuff
START
    intersection $1 $2;
    merge $3 $4;
END




PROG saske
USE dostuff
START
    nop @;  -> [$1, $2, $3, $4]
    nop @;  -> [$1, $2, $3, $4]
            -> [$1, $2, $3, $4, $1, $2, $3, $4]
| 
    dostuff $1..4['phone'];    -> [$1]
    dostuff $5..8['name']      -> [$2]
|
    nop $1;
    sort $2['name']
END




| intersection $1 $2; intersection $3 $4;
| merget $1 $2


# Declaraçção de input
INPUT @             -> Recebe um número qualquer de listas (essa é a opção inferida caso a declaração INPUT seja omitida)
INPUT $1, $2, $3    -> Cada lista que será recebida

## Declaração de colunas
 - $1[] -> Tem cabeçalho no arquivo, mas nn temos detalhes
 - as colunas podem  ser acessadar através do número da coluna ou do nome do cabeçalho, mesmo ele não tendo sido declarado
INPUT $1[]

INPUT @[]  -> Qualquer número de lista com qualquer tipo de cabeçalho
INPUT @['col1', 'col2'] -> Qualquer número de listas, com tipos específicos de cabeçalhos

// $1['col1', 'col2'] -> O arquivo tem um cabeçalho e ele estará nesse formato específico. Quaso o cabeçalho seja inválido, o programa gerará um erro
INPUT $1['phone', 'name']



INPUT $1[] $2['phone', 'name']
INPUT @[]
| intersection $1 $2['phone']; diff $2['phone'] $1
| 






# Opções
--header -> Se presente, 


# Gramática syntaxe whatever
unique $1 $2; intersection $1 $2;

diff $1 $2 === diff; 

symdiff $1 $2


unique $1['phone'] $2['number']; intersection $1['phone'] $2['number']; select $1['number']

unique $1['phone'] $2['number']; sort $2['phone'] >; select $1['number']


# Se omitir as litas, todas serão passadas
unique; intersection;


# De acordo com o número de colunas
## Listas com apenas uma coluna
unique $1 $2; intersection $1 $2

## Caso a lista possua headers definidos
unique $1['phone'] $2['number'];  intersection $1['phone'] $2['phone']

## n-th column
unique $1[0] $2[0]; intersection $1[0] $2[0]


# Operações
union -> elementos que estão em um set ou outro (resultados são únicos)
merge -> combinas todos elementos de todas as listas
