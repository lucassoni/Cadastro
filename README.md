Trabalho Paradigmas de Programação

Equipe:
Alex Matsuo GRR20196993
Lucas Soni Teixeira GRR20190395
Vinicius Matheus Comarella Ferreira GRR20196048

Implementamos o Sistema de Controle de Informações Médicas da Pessoa (SISCOIMP)

Para compilar basta rodar o comando make
e para execução java -jar sistema.jar

Como em algumas partes do sistema é solicitado o caminho
de imagens e vídeos para serem cadastradas, por convêniencia
existem 1 imagem e 1 video padrão no diretório raíz.
Então quando for solicitado o caminho para alguma imagem basta
inserir: ./pessoa.png
E quando for solicitado o caminho para algum vídeo basta inserir:
./colonoscopia.mp4

Implementamos uma interface modo texto.

Nosso projeto está dividido em algumas classes, implementamos os padrões solicitados 
nas especificações em diversas situações.

Padrão DAO e MVC:
    Temos separadas classes que atuam como:
        Model: 
            Controlar os dados e salvar no arquivo em disco
            Classes: Consulta, Exame, Familiar, Paciente, InfoEme, Pessoa.
        Controller: 
            Receber as solicitações da view, tratar, mandar para o model, e devolver
            a resposta para a view.
            Classes: Cadastro, Listagem, Edicao.
        View:
            Receber entrada do usuário, enviar para o controller tratar e 
            mostrar a resposta.
            Classes: App
    Os models apresentados atuam também como DAO's já que são responsáveis apenas pelo
    acesso aos dados.


Padrão GRASP:
    Seguimos diferentes regras do padrão grasp em algumas classes:
        Controller:
            Como apresentado no MVC, temos algumas classes que atuam como controllers.
            Classes: Cadastro, Listagem, Edicao.
        Creator:
            A principal classe que implementamos o creator é a cadastro, que foi escolhida
            para inicializar praticamente todas as classes, por ter todas as informações
            necessárias para fazer esta inicialização.
        Indirection:
            Como apontamos anteriormente, o padrão MVC está implementado, logo o controller
            é esse intermediário entre as Views e os Models, seguindo o padrão Indirection.

Padrão GOF:
    Implementamos o padrão singleton na classe Listagem, para manter sempre somente uma
    instância dela viva nos arquivos, já que ela guarda todas as informações do sistema.


