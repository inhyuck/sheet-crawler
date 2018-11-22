package io.inhyuck.webservice.component;

import io.inhyuck.webservice.config.CardProperties;
import io.inhyuck.webservice.domain.resume.Card;
import io.inhyuck.webservice.domain.resume.ResumeDesign;
import io.inhyuck.webservice.domain.resume.ResumeDevelop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CardComponent {
    @Autowired
    CardProperties cardProperties;

    public List<Card> getDevelopCardList(ResumeDevelop resumeDevelop) {
        List<Card> cardList = new ArrayList<>();
        List<String> questionList = cardProperties.getDeveloperQuestions();
        cardList.add(new Card(questionList.get(0), resumeDevelop.getQuestion3()));
        cardList.add(new Card(questionList.get(1), resumeDevelop.getQuestion4()));
        cardList.add(new Card(questionList.get(2), resumeDevelop.getQuestion5()));
        cardList.add(new Card(questionList.get(3), resumeDevelop.getQuestion6()));
        cardList.add(new Card(questionList.get(4), resumeDevelop.getQuestion7()));
        return cardList;
    }

    public List<Card> getDesignerCardList(ResumeDesign resumeDesign) {
        List<Card> cardList = new ArrayList<>();
        List<String> questionList = cardProperties.getDesignerQuestions();
        cardList.add(new Card(questionList.get(0), resumeDesign.getQuestion3()));
        cardList.add(new Card(questionList.get(1), resumeDesign.getQuestion4()));
        cardList.add(new Card(questionList.get(2), resumeDesign.getQuestion5()));
        cardList.add(new Card(questionList.get(3), resumeDesign.getQuestion6()));
        return cardList;
    }

}
