package br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Services;

import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.AlocacaoSala;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.Enums.TempoSala;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Entities.Sala;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Repositories.AlocacaoSalaRepository;
import br.com.femass.ds1.ControledeSalaFEMASSJava.Domain.Repositories.SalaRepository;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.DayOfWeek;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PDFGeneratorService {

    private final SalaRepository salaRepository;
    private final AlocacaoSalaRepository alocacaoSalaRepository;

    public PDFGeneratorService(SalaRepository salaRepository, AlocacaoSalaRepository alocacaoSalaRepository) {
        this.salaRepository = salaRepository;
        this.alocacaoSalaRepository = alocacaoSalaRepository;
    }

    private static final Map<DayOfWeek, List<TempoSala>> horariosPorDia = Map.of(
            DayOfWeek.MONDAY, List.of(TempoSala.TEMPO1, TempoSala.TEMPO3),
            DayOfWeek.TUESDAY, List.of(TempoSala.TEMPO1, TempoSala.TEMPO2, TempoSala.TEMPO3),
            DayOfWeek.WEDNESDAY, List.of(TempoSala.TEMPO1, TempoSala.TEMPO3),
            DayOfWeek.THURSDAY, List.of(TempoSala.TEMPO1, TempoSala.TEMPO2, TempoSala.TEMPO3),
            DayOfWeek.FRIDAY, List.of(TempoSala.TEMPO1, TempoSala.TEMPO2)
    );

    private static final Map<DayOfWeek, String> diasEquivalente = Map.of(
            DayOfWeek.MONDAY, "Segunda-feira",
            DayOfWeek.TUESDAY, "Terça-feira",
            DayOfWeek.WEDNESDAY, "Quarta-feira",
            DayOfWeek.THURSDAY, "Quinta-feira",
            DayOfWeek.FRIDAY, "Sexta-feira"
    );

    private String getHorarioEquivalente(TempoSala tempo, DayOfWeek dia) {
        return switch (tempo) {
            case TEMPO1 -> "18:00";
            case TEMPO2 -> dia == DayOfWeek.FRIDAY ? "19:50" : "19:40";
            case TEMPO3 -> "20:40";
        };
    }

    public byte[] gerarPdfPorDia(DayOfWeek dia) throws IOException {
        List<Sala> salas = salaRepository.findAll();
        List<AlocacaoSala> alocacoes = alocacaoSalaRepository.findByDiaSemana(dia);
        List<TempoSala> tempos = horariosPorDia.getOrDefault(dia, List.of());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document doc = new Document(PageSize.A4);
        PdfWriter.getInstance(doc, out);
        doc.open();

        Font titleFont = new Font(Font.HELVETICA, 14, Font.BOLD);
        Font headerFont = new Font(Font.HELVETICA, 12, Font.BOLD);
        Font normalFont = new Font(Font.HELVETICA, 10);

        Paragraph header = new Paragraph(
                "Faculdade Professor Miguel Ângelo - FEMASS\nQuadro de Horários - " + diasEquivalente.get(dia),
                titleFont
        );
        header.setAlignment(Element.ALIGN_CENTER);
        doc.add(header);
        doc.add(Chunk.NEWLINE);

        for (TempoSala tempo : tempos) {
            for (Map.Entry<String, List<Sala>> bloco : salas.stream().collect(Collectors.groupingBy(Sala::getBloco)).entrySet()) {
                Paragraph blocoTitle = new Paragraph("BLOCO " + bloco.getKey(), headerFont);
                blocoTitle.setAlignment(Element.ALIGN_CENTER);
                doc.add(blocoTitle);
                doc.add(Chunk.NEWLINE);

                PdfPTable tabela = new PdfPTable(5);
                tabela.setWidthPercentage(100);
                Stream.of("TURMA", "DISCIPLINA", "PROFESSOR", "HORÁRIO", "SALA").forEach(titulo -> {
                    PdfPCell cell = new PdfPCell(new Phrase(titulo, headerFont));
                    cell.setBackgroundColor(RGBColor.LIGHT_GRAY);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tabela.addCell(cell);
                });

                for (Sala sala : bloco.getValue().stream().sorted(Comparator.comparing(Sala::getNumero)).toList()) {
                    AlocacaoSala alocacao = alocacoes.stream()
                            .filter(a -> a.getTempo() == tempo && a.getSala().equals(sala))
                            .findFirst()
                            .orElse(null);

                    tabela.addCell(alocacao != null ? String.valueOf(alocacao.getTurma().getId()) : "");
                    tabela.addCell(alocacao != null ? alocacao.getTurma().getDisciplina().getNome() : "");
                    tabela.addCell(alocacao != null ? alocacao.getTurma().getProfessor() : "");
                    tabela.addCell(getHorarioEquivalente(tempo, dia));
                    tabela.addCell(Long.toString(sala.getNumero()));
                }

                doc.add(tabela);
                doc.add(Chunk.NEWLINE);
            }
        }

        doc.close();
        return out.toByteArray();
    }
}