package ua.lviv.iot.databases.lab5.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ErrorMessage {
  private int statusCode;
  private Date timestamp;
  private String message;
  private String description;
}