package ru.yuriymog.live.code;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface IRepositoryObject {
    public void writeObject(ObjectOutputStream repStream) throws IOException;
    public void readObject(ObjectInputStream repStream) throws IOException;
}
