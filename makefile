files = Cadastro.class Familiar.class Medico.class Paciente.class Pessoa.class App.class
dir = src/main/

all: 
	cd $(dir) && make

purge: 
	cd $(dir) && rm -f $(files)
