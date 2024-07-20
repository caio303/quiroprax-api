package com.quiroprax.api.model.enums;

public enum StatusAgendamento {

    AGENDADO(1),
    REALIZADO(2),
    CANCELADO(3),
    REMARCADO(4);

    StatusAgendamento(Integer id) {
        this.id = id;
    }

    private Integer id;

    public static StatusAgendamento forId(Integer statusId) {
        for (StatusAgendamento status : StatusAgendamento.values()) {
            if (status.getId().equals(statusId)) {
                return status;
            }
        }
        return null;
    }

    public Integer getId() {
        return id;
    }
}
