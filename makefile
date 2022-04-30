files = Cadastro.class Familiar.class Medico.class Paciente.class Pessoa.class App.class Listagem.class InfoEme.class Edicao.java
dir = src/main/

all: 
	cd $(dir) && make

purge: 
	cd $(dir) && rm -f $(files)
