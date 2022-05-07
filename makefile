dir = src/main/

files = Cadastro.class Familiar.class Medico.class Paciente.class Pessoa.class App.class Listagem.class Consulta.class Exame.class InfoEme.class Edicao.class

all: $(files) sistema.jar

App.class: $(dir)App.java Cadastro.class Familiar.class Medico.class Paciente.class Listagem.class Consulta.class Exame.class InfoEme.class Edicao.class
	javac $(dir)App.java

Cadastro.class: $(dir)Cadastro.java Paciente.class Familiar.class Paciente.class Listagem.class Consulta.class Exame.class InfoEme.class Edicao.class
	javac $(dir)Cadastro.java

Edicao.class: $(dir)Edicao.java
	javac $(dir)Edicao.java

Familiar.class: $(dir)Familiar.java 
	javac $(dir)Familiar.java

InfoEme.class: $(dir)InfoEme.java
	javac $(dir)InfoEme.java

Medico.class: $(dir)Medico.java 
	javac $(dir)Medico.java

Paciente.class: $(dir)Paciente.java 
	javac $(dir)Paciente.java

Pessoa.class: $(dir)Pessoa.java 
	javac $(dir)Pessoa.java

Listagem.class: $(dir)Listagem.java
	javac $(dir)Listagem.java
	
Consulta.class: $(dir)Consulta.java 
	javac $(dir)Consulta.java

Exame.class: $(dir)Exame.java	
	javac $(dir)Exame.java

sistema.jar: $(files)
	jar cfe sistema.jar src.main.App src/main/*.class

purge: 
	rm -rf sistema.jar && cd $(dir) && rm -rf *.class
