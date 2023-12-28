package fctk.server;

import fctk.server.exceptions.ServerException;

public interface IServer {
    String execute(String json, String endPoint) throws ServerException;
}
