package com.springframework.samples.madaja.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SeguroCliente")
public class SeguroCliente extends Seguro { //ASOCIADO 1 A 1 A CLIENTE

}