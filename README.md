# Sistema de constrole de turmas
Sistema acadêmico para gerenciamento de turmas, desenvolvido como projeto final da disciplina de Linguagem de Programação utilizando Java.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)

## Sobre
### Principais funcionalidades
- Cadastro e gerenciamento de alunos
- Cadastro e gerenciamento de disciplinas
- Cadastro e gerenciamento de professores
- Gerenciamento de turmas
- Geração de relatórios e persistência de dados

### Estrutura do projeto
```
src/
├── model/
│   ├── disciplina/        # Classe Disciplina
│   ├── turma/             # Classes turma e nota
│   │   └── tiposDeMedia/  # estratégias de média
│   ├── pessoa/            # Classes Pessoa (abstrata), Aluno, Professor
│   ├── servicos/          # Serviços de gerenciamento
│   ├── exception/         # Exceções customizadas
│   ├── faculdade/         # Fachada do sistema
│   └── main/              # Ponto de entrada
├── data/                  # Ponto de saída dos relatórios e persistênncia 
└── tests/                 # Testes unitários
```

## Contribuidores
| ![Foto](https://github.com/JoaoBoscoDuarte.png?size=100) | ![Foto](https://github.com/Randerson7-png.png?size=100) | ![Foto](https://github.com/Annabdv.png?size=100) |
|----------------------------------------------------|-------------------------------------------------------|-----------------------------------------------------|
| **João Bosco** [@JoaoBoscoDuarte](https://github.com/JoaoBoscoDuarte) | **Randerson** [@Randerson7-png](https://github.com/Randerson7-png) | **Anna Beatriz** [@Annabdv](https://github.com/Annabdv) |
