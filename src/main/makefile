files = Cadastro.class Familiar.class Medico.class Paciente.class Pessoa.class App.class Listagem.class Consulta.class Exame.class

all: $(files)

App.class: App.java Cadastro.class Familiar.class Medico.class Paciente.class Listagem.class Consulta.class Exame.class
	javac App.java

Cadastro.class: Cadastro.java Paciente.class Familiar.class Paciente.class Listagem.class Consulta.class Exame.class
	javac Cadastro.java

Familiar.class: Familiar.java 
	javac Familiar.java

Medico.class: Medico.java 
	javac Medico.java

Paciente.class: Paciente.java 
	javac Paciente.java

Pessoa.class: Pessoa.java 
	javac Pessoa.java

Listagem.class: Listagem.java
	javac Listagem.java
	
Consulta.class: Consulta.java 
	javac Consulta.java

Exame.class: Exame.java	
	javac Exame.java

purge: 
	rm -f $(files)

