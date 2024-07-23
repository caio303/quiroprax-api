package com.quiroprax.api.model.assembler;

public interface BaseAssembler<Entidade, DTO> {

    Entidade paraEntidade(DTO dto);

    DTO paraDTO(Entidade dto);

}
