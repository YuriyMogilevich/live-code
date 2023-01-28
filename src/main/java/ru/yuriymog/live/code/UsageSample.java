package ru.yuriymog.live.code;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;


public class UsageSample {

    public static void someWhereInCode(ObjectInputStream) throws IOException {
        DocumentCard card;


        card = new DocumentCard("Some General Document", new int[]{DocumentCard.CREDITDOC,
                DocumentCard.GENERALDOC});
        card.addAttributeValue("creditNumber", "111875012-23");
        card.addAttributeValue("creditDate", new Date());
        card.addAttributeValue("creditValue", "2000");
        card.writeObject(stream);

        card = new DocumentCard("Some Payment", new int[]{DocumentCard.PAYMENTDOC,
                DocumentCard.GENERALDOC});
        card.addAttributeValue("payment", "123875012");
        card.addAttributeValue("amount", "8000");
        card.addAttributeValue("docDate", new Date());
        card.writeObject(stream);

        card = new DocumentCard("Some Vip Document", new int[]{DocumentCard.CREDITDOC,
                DocumentCard.VIPDOC});
        card.addAttributeValue("creditNumber", "5558750`1-23");
        card.addAttributeValue("creditDate", new Date());
        card.addAttributeValue("creditValue", "3000000");
        card.writeObject(stream);

        card = new DocumentCard("Some Document", new int[]{DocumentCard.HERITAGEDOC});
        card.addAttributeValue("creditNumber", "219-18-1975");
        card.addAttributeValue("docDate", new Date());
        card.writeObject(stream);
    }

}

    public static void readTestObject(ObjectInputStream stream) throws IOException {
        DocumentCard card = new DocumentCard("", null);
        card.readObject(stream);
        String credNumber = (String) card.getAttributeValue("creditNumber");
        Date date = (Date) card.getAttributeValue("creditDate");
        int credValue = Integer.parseInt((String) card.getAttributeValue("creditValue"));
    }
}
