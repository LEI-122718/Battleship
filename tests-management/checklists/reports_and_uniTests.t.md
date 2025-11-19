# reports_and_unitTests

##  Objetivo do Checklist
Este Test Suite descreve as operações que devem ser executadas durante o processo de verificação do projeto, nomeadamente:
- Geração de relatórios (TMS, Allure, Coverage)
- Execução de testes unitários automáticos (JUnit)
- Validação dos resultados e métricas de qualidade

## ✔ Operações a Executar

### C1 – Geração de Relatórios
* Verificar se o plugin TMS está ativado
* Gerar relatório Markdown dos testes no IntelliJ
* Confirmar que Allure Java Commons está disponível no projeto

### C2 – Execução de Testes Unitários
* Executar todos os testes JUnit
* Validar sucesso/falha de testes individuais
* Repetir execução com *Run With Coverage*
* Confirmar linhas/métodos/classes cobertas

### C3 – Integração com Test Management
* Confirmar que os ficheiros *.t.md são criados corretamente
* Garantir que referências de código (ii) estão ativadas
* Validar que o Test Suite aparece na sidebar do TMS
