package com.example.messagegenerator.messageModifierHelper;

import org.immregistries.smm.transform.ModifyMessageRequest;
import org.immregistries.smm.transform.ModifyMessageService;

public class MessageModifierHelper {



    public String ex = "MSH|^~\\&||AIRA|GRITS|GRITS|20150817082226-0600||VXU^V04^VXU_V04|J94H2.1D6|P|2.5.1|\r"
            + "PID|1||J94H2^^^AIRA-TEST^MR||Butte^Rahman^Jaiden^^^^L|Marion^Donla|20150220|M|||371 Franklin Cir^^Alma^MI^48802^USA^P||^PRN^PH^^^989^4479902|\r"
            + "NK1|1|Butte^Donla|MTH^Mother^HL70063|\r" + "ORC|RE||J94H2.1^AIRA|\r"
            + "RXA|0|1|20150421||10^IPV^CVX|999|||01^Historical^NIP001||||||||||||A|\r"
            + "ORC|RE||J94H2.2^AIRA|\r"
            + "RXA|0|1|20150621||10^IPV^CVX|999|||01^Historical^NIP001||||||||||||A|\r"
            + "ORC|RE||J94H2.3^AIRA|||||||I-23432^Burden^Donna^A^^^^^NIST-AA-1||57422^RADON^NICHOLAS^^^^^^NIST-AA-1^L|\r"
            + "RXA|0|1|20150817||20^DTaP^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||^^^25966||||G3592YS||PMC^sanofi pasteur^MVX||||A|\r"
            + "OBX|1|CE|64994-7^Vaccine funding program eligibility category^LN|1|V01^Not VFC eligible^HL70064||||||F|||20150817|||VXC40^Eligibility captured at the immunization level^CDCPHINVS|\r";
    public String modifyMessage(String originalMessage, String transformScript) {
        ModifyMessageService modifyMessageService = new ModifyMessageService();
        ModifyMessageRequest modifyMessageRequest = new ModifyMessageRequest();
        modifyMessageRequest.setMessageOriginal(originalMessage);
        modifyMessageRequest.setTransformScript(transformScript);
        modifyMessageService.transform(modifyMessageRequest);
        return modifyMessageRequest.getMessageFinal();
    }


}
