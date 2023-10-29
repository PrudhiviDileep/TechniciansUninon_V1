package com.org.telugucineandtvoutdoorunittechniciansunion.service;

import com.org.telugucineandtvoutdoorunittechniciansunion.DAO.MembershipDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembershipService {
  @Autowired
  public MembershipDAO membershipDAO;
}
