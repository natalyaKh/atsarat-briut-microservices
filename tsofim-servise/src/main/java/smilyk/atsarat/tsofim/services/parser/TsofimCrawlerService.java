package smilyk.atsarat.tsofim.services.parser;

import org.springframework.stereotype.Service;

@Service
public interface TsofimCrawlerService {
    String sendFormToTsofim(String uuidChild);
}
