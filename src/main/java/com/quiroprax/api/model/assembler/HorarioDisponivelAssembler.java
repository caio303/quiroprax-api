package com.quiroprax.api.model.assembler;

import com.quiroprax.api.model.HorarioDisponivel;
import com.quiroprax.api.model.dto.CadastroHorariosDisponiveisDTO;
import com.quiroprax.api.model.dto.HorarioDisponivelDTO;

import java.util.ArrayList;
import java.util.List;

public class HorarioDisponivelAssembler implements BaseAssembler<HorarioDisponivel, HorarioDisponivelDTO> {

    @Override
    public HorarioDisponivel paraEntidade(HorarioDisponivelDTO horarioDisponivelDTO) {
        var dataHora = horarioDisponivelDTO.dataHora().split(" ");
        var data = dataHora.length > 0 ? dataHora[0] : null;
        var hora = dataHora.length > 1 ? dataHora[1] : null;

        var horario = new HorarioDisponivel();
        horario.setId(horarioDisponivelDTO.id());
        horario.setData(data);
        horario.setHora(hora);

        return horario;
    }

    @Override
    public HorarioDisponivelDTO paraDTO(HorarioDisponivel dto) {
        return new HorarioDisponivelDTO(dto);
    }

    public List<HorarioDisponivel> paraEntidades(List<CadastroHorariosDisponiveisDTO> novosHorariosDTO) {
        var listaHorarios = new ArrayList<HorarioDisponivel>();

        novosHorariosDTO.forEach(cadastroHorariosDTO -> {
            listaHorarios.addAll(this.paraEntidades(cadastroHorariosDTO));
        });

        return listaHorarios;
    }

    private List<HorarioDisponivel> paraEntidades(CadastroHorariosDisponiveisDTO cadastroHorariosDisponiveisDTO) {
        var listaHorarios = new ArrayList<HorarioDisponivel>();
        var data = cadastroHorariosDisponiveisDTO.data();

        cadastroHorariosDisponiveisDTO.horarios()
                .forEach(horario -> {
                    listaHorarios.add(this.paraEntidade(data, horario));
                });

        return null;
    }

    private HorarioDisponivel paraEntidade(String data, String horario) {
        var horarioDisponivel = new HorarioDisponivel();
        horarioDisponivel.setData(data);
        horarioDisponivel.setHora(horario);
        return horarioDisponivel;
    }


}
