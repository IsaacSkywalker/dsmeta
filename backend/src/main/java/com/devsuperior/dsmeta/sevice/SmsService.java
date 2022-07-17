package com.devsuperior.dsmeta.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {

	@Value("${twilio.sid}")
	private String twilioSid;

	@Value("${twilio.key}")
	private String twilioKey;

	@Value("${twilio.phone.from}")
	private String twilioPhoneFrom;

	@Value("${twilio.phone.to}")
	private String twilioPhoneTo;
	
	@Autowired
	private SaleRepository saleRepository;
	
	public void sendSms(Long saleId) {
		/*Acessando a tabela através do "saleRepository", procurando os dados pelo id utilizando
		 * ".findById(saleId)" e obtendo esse valor com o ".get()". Deveremos criar uma variável
		 * tipo "Sale", pois é a classe que contém as informações da tabela*/
		Sale sale = saleRepository.findById(saleId).get();
		
		/*Criando uma variável para armazenar a data*/
		String date = sale.getDate().getMonthValue() + "/" + sale.getDate().getYear();
		
		/*Definido a mensagem que irá aparecer no sms*/
		String msg = "O Vendedor " + sale.getSellerName() + " foi destaque em " + date
				+ " com um total de R$ " + String.format("%.2f", sale.getAmount());
		
		Twilio.init(twilioSid, twilioKey);

		PhoneNumber to = new PhoneNumber(twilioPhoneTo);
		PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

		Message message = Message.creator(to, from, msg).create();

		System.out.println(message.getSid());
	}
}
