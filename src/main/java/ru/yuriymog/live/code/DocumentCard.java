package ru.yuriymog.live.code;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;

public class DocumentCard implements IRepositoryObject {


    //document flags
    public static final int GENERALDOC = 1;

    public static final int VIPDOC = 2;

    public static final int PAYMENTDOC = 4;

    public static final int CREDITDOC = 8;

    public static final int HERITAGEDOC = 16;

    private String objectName;
    private Hashtable attributes;
    private int[] documentFlags;

    public DocumentCard(int[] documentFlags) {
        this.objectName = "New Object";
        this.attributes = new Hashtable();
        this.documentFlags = documentFlags;
    }

    public DocumentCard(String objectName, int[] documentFlags) {
        this(documentFlags);
        this.objectName = objectName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) throws GeneralANBException {
        if (objectName == null)
            throw new GeneralANBException("Illegal object name!");

        this.objectName = objectName;
    }

    public void addAttributeValue(Object key, Object value) {
        if (key != null && value != null)
            attributes.put(key, value);
    }

    public Object getAttributeValue(Object key) {
        return (key != null) ? attributes.get(key) : null;
    }

    public void writeObject(ObjectOutputStream repStream) throws IOException {
        repStream.writeObject(objectName);
        int objType = 0;
        for (int i = 0; i < documentFlags.length; i++)
            objType += documentFlags[i];
        repStream.writeObject(String.valueOf(objType));
        int size = attributes.keySet().size();
        if (size > 0) {
            Object key;
            Iterator iterator = attributes.keySet().iterator();
            while (iterator != null && iterator.hasNext()) {
                key = iterator.next();
                repStream.writeObject(key);
                repStream.writeObject(attributes.get(key));
            }
        }
    }

    private void addDocumentFlag(int flag) {
        documentFlags = Arrays.copyOf(documentFlags, documentFlags.length + 1);
        documentFlags[documentFlags.length - 1] = flag;
    }

    public void readObject(ObjectInputStream repStream) throws IOException {
        try {
            objectName = (String) repStream.readObject();
            int objType = Integer.parseInt((String) repStream.readObject());
            documentFlags = new int[];
            if ((objType & GENERALDOC) != 0)
                addDocumentFlag(GENERALDOC);
            if ((objType & VIPDOC) != 0)
                addDocumentFlag(VIPDOC);
            if ((objType & PAYMENTDOC) != 0)
                addDocumentFlag(PAYMENTDOC);
            if ((objType & CREDITDOC) != 0)
                addDocumentFlag(CREDITDOC);
            if ((objType & HERITAGEDOC) != 0)
                addDocumentFlag(HERITAGEDOC);
            attributes = new Hashtable();
            int size = Integer.parseInt((String) repStream.readObject());
            while (size > 0) {
                Object key = repStream.readObject();
                Object value = repStream.readObject();
                attributes.put(key, value);
                size--;
            }
        } catch (Throwable e) {
            throw new IOException(new GeneralANBException("Something broken"));
        }
    }
}
