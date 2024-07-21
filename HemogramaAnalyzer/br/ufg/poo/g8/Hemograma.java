package br.ufg.poo.g8;

import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.dao.ForeignCollection;

@DatabaseTable(tableName = "hemograma")
public class Hemograma {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Paciente paciente;

    @DatabaseField
    private double hemoglobina;

    @DatabaseField
    private double leucocitos;

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
    public double getHemoglobina() { return hemoglobina; }
    public void setHemoglobina(double hemoglobina) { this.hemoglobina = hemoglobina; }
    public double getLeucocitos() { return leucocitos; }
    public void setLeucocitos(double leucocitos) { this.leucocitos = leucocitos; }
}
