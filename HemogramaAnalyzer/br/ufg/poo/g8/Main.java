package br.ufg.poo.g8;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Database db = new Database("health.db");
        PacienteRepository pacienteRepo = new PacienteRepository(db);
        HemogramaRepository hemogramaRepo = new HemogramaRepository(db);

        // Create Paciente
        Paciente paciente = new Paciente();
        paciente.setNome("João da Silva");
        paciente.setIdade(30);
        pacienteRepo.create(paciente);

        // Create Hemograma
        Hemograma hemograma = new Hemograma();
        hemograma.setPaciente(paciente);
        hemograma.setHemoglobina(13.5);
        hemograma.setLeucocitos(6500);
        hemogramaRepo.create(hemograma);

        // Retrieve
        Paciente loadedPaciente = pacienteRepo.loadFromId(paciente.getId());
        System.out.println("Paciente: " + loadedPaciente.getNome());

        // Retrieve Hemograma
        Hemograma loadedHemograma = hemogramaRepo.loadFromId(hemograma.getId());
        System.out.println("Hemograma: Hemoglobina=" + loadedHemograma.getHemoglobina() + ", Leucocitos=" + loadedHemograma.getLeucocitos());

        // Update
        loadedPaciente.setNome("João Souza");
        pacienteRepo.update(loadedPaciente);

        // Delete
        hemogramaRepo.delete(loadedHemograma);
        pacienteRepo.delete(loadedPaciente);

        db.close();
    }
}
