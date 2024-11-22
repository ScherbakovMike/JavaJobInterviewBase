package com.mikescherbakov.jobinterviewbase.services;

import com.mikescherbakov.jobinterviewbase.model.SomeBean;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BeanService {

  private final SomeBean someBean;

  @Autowired
  private SomeBean fieldInjection;

  public void printSomeBean() {
    System.out.printf("BeanService.printSomeBean: hash: %d%n", this.someBean.hashCode());
  }

  public void fieldInjection() {
    System.out.printf("BeanService.fieldInjection: hash: %d%n", fieldInjection.hashCode());
  }

  public void setterInjection() {
    System.out.printf("BeanService.setterInjection: hash: %d%n", someBean.hashCode());
  }
}
