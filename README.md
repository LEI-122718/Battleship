# Battleship

Basic academic version of Battleship game to build upon.

Carolina Torres, 122718
Matilde Marcelino, 122695
Ruama Felix, 122662

# Analise sobre o workflow java with maven:

Este workflow do GitHub Actions é usado para compilar automaticamente um projeto Java com Maven sempre que houver:
um push para a branch main ou um pull request para a branch main.

O que ele faz:

Faz checkout do repositório

Baixa o código para a máquina virtual do GitHub Actions.

Instala o Java 17 (Temurin)

Prepara o ambiente e ativa o cache das dependências do Maven para acelerar execuções futuras.

Compila o projeto com Maven

Executa mvn package para compilar, correr testes e gerar o .jar.

Ajuda o Dependabot a detetar vulnerabilidades nas dependências do projeto.

2.0