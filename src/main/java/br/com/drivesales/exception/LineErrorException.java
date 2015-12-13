/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.exception;

/**
 *
 * @author thomas
 */
public class LineErrorException extends RuntimeException {
    private String lineError;
    private Long lineNumber;
    
    public LineErrorException() {
        super();
    }

    public LineErrorException(String lineError, Long lineNumber) {
        this.lineError = lineError;
        this.lineNumber = lineNumber;
    }

    public LineErrorException(String message, Long lineNumber, Throwable cause) {
        super(cause);
        this.lineError = message;
        this.lineNumber = lineNumber;
    }

    public LineErrorException(Throwable cause) {
        super(cause);
    }

    public String getLineError() {
        return lineError;
    }

    public void setLineError(String lineError) {
        this.lineError = lineError;
    }

    public Long getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Long lineNumber) {
        this.lineNumber = lineNumber;
    }
}
