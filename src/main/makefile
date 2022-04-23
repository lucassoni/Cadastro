files = Cadastro.class Familiar.class Medico.class Paciente.class Pessoa.class App.class

all: $(files)

App.class: App.java Cadastro.class Familiar.class Medico.class Paciente.class
	javac App.java

Cadastro.class: Cadastro.java Paciente.class Familiar.class Paciente.class
	javac Cadastro.java

Familiar.class: Familiar.java 
	javac Familiar.java

Medico.class: Medico.java 
	javac Medico.java

Paciente.class: Paciente.java 
	javac Paciente.java

Pessoa.class: Pessoa.java 
	javac Pessoa.java

purge: 
	rm -f $(files)

