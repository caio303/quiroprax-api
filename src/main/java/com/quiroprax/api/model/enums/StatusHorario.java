package com.quiroprax.api.model.enums;

public enum StatusHorario implements StatusWithId {

    DISPONIVEL(1),
    AGENDADO(2);

    private int id;

    StatusHorario(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
