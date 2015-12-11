/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.test.domain;

import br.com.drivesales.domain.Branch;
import br.com.drivesales.domain.Company;
import br.com.drivesales.domain.Sale;
import br.com.drivesales.service.ProcessStream;
import br.com.drivesales.util.DelimitersEnum;
import br.com.drivesales.util.HeaderTypes;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import static org.hamcrest.Matchers.is;
import org.junit.Assert;
import static org.junit.Assert.assertThat;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import static ch.lambdaj.Lambda.*;

import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.select;
import ch.lambdaj.group.Group;

/**
 *
 * @author thomas
 */
public class CompanyParseTest {

    private final Logger logger = LoggerFactory.getLogger(CompanyParseTest.class);
    ProcessStream<Company> processStream = new ProcessStream<>();

    @Test
//    @Ignore
    public void shouldReadALineAndPopulateASaleFromCompany() throws InstantiationException, IllegalAccessException, IOException {

        String line = "São Paulo	Janeiro	14.558,00 ";
        Company company = this.processStream.parseToEntity(line, HeaderTypes.FILIAL_PERIODO_TOTAL, DelimitersEnum.TAB);

        assertThat(company.getBranchs().isEmpty(), is(false));
        assertThat(company.getBranchs().iterator().next().getSales().isEmpty(), is(false));

    }

    @Test
//    @Ignore
    public void shouldReadAFileAndGetAffiliateSoldMore() throws UnsupportedEncodingException, IOException, InstantiationException, IllegalAccessException {
        logger.info("ENTER shouldReadATextAndFindItsHeadersAndDomain");

        InputStreamReader in = new InputStreamReader(this.getClass().getResourceAsStream("/movimentacoes.txt"), "latin1");
        long start_time = System.currentTimeMillis();
        Company company = this.processStream.parseToEntity(in, DelimitersEnum.TAB);
        long end_time = System.currentTimeMillis();

        logger.info("\t Tempo total : " + (end_time - start_time) + "ms");
        //166ms
        //10620ms

        Assert.assertNotNull(company);
        Assert.assertNotNull(company.getBranchs());
        assertThat(company.getBranchs().isEmpty(), is(false));

        List<Branch> branchs = company.getBranchs();
        Group<Branch> group = group(branchs, by(on(Branch.class).getLocation()));
        

        for (Branch branch : company.getBranchs()) {
            logger.info("\t" + branch.getName() + branch.getLocation());
            List<Sale> sales = branch.getSales();
            for (Sale sale : sales) {
                logger.info("\t\t" + sale.getMonthPeriod().getInicialDate() + "|" + sale.getMonthPeriod().getFinalDate());
                logger.info("\t\t" + sale.getTotal());
            }

        }

        logger.info("\t" + "SUCESS");
        logger.info("END shouldReadATextAndFindItsHeadersAndDomain");
    }

}
