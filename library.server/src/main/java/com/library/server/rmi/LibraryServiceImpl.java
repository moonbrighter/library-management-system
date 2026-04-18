package com.library.server.rmi;

import com.library.shared.rmi.ILibraryService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LibraryServiceImpl extends UnicastRemoteObject implements ILibraryService {

    public LibraryServiceImpl() throws RemoteException {
        super();
    }
}
