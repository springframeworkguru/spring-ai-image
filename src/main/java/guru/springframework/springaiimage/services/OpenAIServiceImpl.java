package guru.springframework.springaiimage.services;

import guru.springframework.springaiimage.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.openai.OpenAiImageClient;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

import java.util.Base64;

/**
 * Created by jt, Spring Framework Guru.
 */
@RequiredArgsConstructor
@Service
public class OpenAIServiceImpl implements OpenAIService {

    final OpenAiImageClient imageClient;

    @Override
    public byte[] getImage(Question question) {

        var options = OpenAiImageOptions.builder()
                .withHeight(1024).withWidth(1792)
                .withResponseFormat("b64_json")
                .withModel("dall-e-3")
                .withQuality("hd") //default standard
                //.withStyle("natural") //default vivid
                .build();

        ImagePrompt imagePrompt = new ImagePrompt(question.question(), options);

        var imageResponse = imageClient.call(imagePrompt);

        return Base64.getDecoder().decode(imageResponse.getResult().getOutput().getB64Json());
    }
}


















