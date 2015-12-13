/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.repository;

import br.com.drivesales.domain.Branch;
import br.com.drivesales.dto.LocationAndTotalDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author thomas
 */
public interface BranchRepository extends JpaRepository<Branch, Long>{
    
    @Query("select new br.com.drivesales.dto.LocationAndTotalDTO(b.location, SUM(s.total) as total) \n" +
        "	from\n" +
        "	    Branch b\n" +
        "	        inner join\n" +
        "	    b.sales s \n" +
        "group by b.location \n" +
        "order by total desc")
    public List<LocationAndTotalDTO> getMostSold();
    
    //TODO improve query and create one for LessIncrease
    @Query("select new br.com.drivesales.dto.LocationAndTotalDTO(b.location, s.total) \n" +
        "	from\n" +
        "	    Branch b\n" +
        "	        inner join\n" +
        "	    b.sales s \n" +
        "group by b.location, s.total \n" +
        "order by b.location, s.total desc")
    public List<LocationAndTotalDTO> getBranchMoreIncrease();
}
