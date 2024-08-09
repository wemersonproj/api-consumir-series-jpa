package br.com.alura.screenmatch.service.traducao;

import br.com.alura.screenmatch.service.ConsumoApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.net.URLEncoder;

public class ConsultaMyMemory {
    public static String obterTraducao(String text){
        ObjectMapper mapper = new ObjectMapper();
        ConsumoApi consumo = new ConsumoApi();

        String texto = URLEncoder.encode(text);
        String langpair = URLEncoder.encode("autodetect|pt-br");
        String url = "https://api.mymemory.translated.net/get?q=" + texto + "&langpair=" + langpair;
        String json = consumo.obterDados(url);

        DadosTraducao dadosTraducao;
        try{
            dadosTraducao = mapper.readValue(json, DadosTraducao.class);
        }catch (JsonProcessingException e){
            throw new RuntimeException();
        }
        return dadosTraducao.dadosResposta().textoTraduzido();



    }
}
