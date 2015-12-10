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
public class MonthNotFoundException extends RuntimeException {
    private String lineError;
    
    public MonthNotFoundException() {
        super();
    }

    public MonthNotFoundException(String lineError) {
        this.lineError = lineError;
    }

    public MonthNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MonthNotFoundException(Throwable cause) {
        super(cause);
    }

    public String getLineError() {
        return lineError;
    }

    public void setLineError(String lineError) {
        this.lineError = lineError;
    }
}
