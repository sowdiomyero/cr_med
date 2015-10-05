/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.esecure.banking.service;

import fr.esecure.banking.modele.client.entities.JobExecutorHistory;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author sniang
 */
public interface IEsecureDaoJobExecutor extends CrudRepository<JobExecutorHistory, Long>{

}
