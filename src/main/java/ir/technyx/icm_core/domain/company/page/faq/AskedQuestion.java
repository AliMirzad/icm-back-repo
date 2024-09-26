package ir.technyx.icm_core.domain.company.page.faq;

import ir.technyx.icm_core.domain.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_asked_question")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AskedQuestion extends BaseEntity {

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_faq", nullable = false)
    private Faq faq;

    @Column(name = "question", nullable = false)
    private String question;

    @Column(name = "answer", nullable = false)
    private String answer;


    public AskedQuestion(String question, String answer) {
        setQuestion(question);
        setAnswer(answer);
    }

    public AskedQuestion(Long id, Faq faq, String question, String answer) {
        setId(id);
        setFaq(faq);
        setQuestion(question);
        setAnswer(answer);
    }
}
