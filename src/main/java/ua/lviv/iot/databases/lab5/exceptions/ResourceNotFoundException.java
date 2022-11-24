package ua.lviv.iot.databases.lab5.exceptions;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String msg) {
    super(msg);
  }
}