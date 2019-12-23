import com.skysharing.api.DefaultCassPayClient;
import com.skysharing.api.exception.InvalidPrivateKeyException;
import com.skysharing.api.exception.InvalidPublicKeyException;
import com.skysharing.api.exception.ResponseNotValidException;
import com.skysharing.api.model.AliPayOrder;
import com.skysharing.api.model.BankPayOrder;
import com.skysharing.api.model.RemitOrder;
import com.skysharing.api.request.*;
import com.skysharing.api.response.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientTest {
    private String privateKeyStr = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDmtPj23EPTcpi6S1BppKJzR5+4ab3CWROpYrgdDFlbpO/ia++ckFdJyrgEjpRE3L4veCGpmshC8bG2xH9UaI5edIuC5WXy0FkW2oKFR6dnPpSv22GM/xNFOIGOVqsex9tZbJbfhmyYuWpUbHXlga2KCxbQosYktuWjAEzb+sfFngGVaI7CS5NSUGFhR3xwpEzO8VoViPi66qVQavoxkreG/uKzoI9I7Ai3b5JK+vA+Gz7W+ZpAqJPZihdvmqaKIt7QL7A4+tV8OWzhELyaIsIJ33RfsGe+iLp7XAfh1dm4bfzFIcUS3ws2ZE0ywb3TCurXMYqeLjsiVr42wYjPiHwdAgMBAAECggEAJqC0crjMjnHIiqCjlRygqoaagokJ4amCdkD2LL7tkz+ZfqKt2tv8EXnkt7absq/3FAGcOUaWM0c+hyh9XUeoVr5SwZbhK/eggwgRBHnL2KiMwqkLu0zWECf24Ts3qY6y9lUNKd3a/vNEj2AdmVDOGqfiqQon/Ou2pUUemVJy8m5BwYLafp5tZk/Yvo5ns48I6recTaQiCTbwXlD0hSYVHhaOhtzIXLk5hjBKGasDNuvAmhqZM+XGnITTq4Z+wGZS3Ir4ynGZMn4FzzTXd41SZ/tucKjFmh3tSeCQjTBzKS+Y6je694UNCKy0TTFc9mANFs4qL+m58uD6KWUiRx/27QKBgQD/FgJQPB2W6Yw92vTS1fJA1irJLPeUoy3R99qDAlGjbnwht31943/DfhBtBGdEOUaXskJvtZLYwU4aE9CytUKQeqhuIU1uRosE44V3wq6EFTknXGFQ+XAT9e+hAjSn7HcHYjPRvxMJvmg3N/86oQAq/Zt6UNGmvCRwQzSdmdeUmwKBgQDniJm7v56xVuO/5W+P9yiNUayHgCfya7/eQij0Rw9hIJ0mKEUl2ENGGdtw6ZqsdrSD6yHkzGLTGCd0cy9cifwz25AjnSOf5kLHJem9WuxqcFlZQlF8X+ba1OOJwfQxyCrjhR2/1QttVGOl84kMDij/YYA0Ip+EQ5mP5YWxsfzRpwKBgGHX3VdNV8Q1HRf0zoe4jM2V2J+F4glfdgnd2jD6SLo5fN+p9Da/dphT2JUYZUvj9FHnjFgw+3ys2Ppjs19462ljwwtLNtofsN9VLjK7uBjt0xhn9OQMaZMeeKX3pJ7sstNgNaQ0eGb6ZBsaHYoti1TyJhsKuQANlqSBAsOY6PoVAoGAfpjIRkoeASVbXj+bYoJn3+16gpRmQzz7KhHOxtJb7GWix4xZUroO/rOAsxpoAkjdpwvX+nxuxLF+UUPx741bIxe4lmCDbBjBBpcKWQjwH7rSf+WtHG5rkde5mkc8uEOUf9b3Tz60AtGTsteYZckQTaMIMHmF8xHrUyzSjzic3h0CgYEApidFokFSKY39OLk9WqWLd+XfL1ykOJh4xsREADo09oIvVtRrtaojaDpCg6/ewb5eVHzIxutTJqrKNIiO00+jK9OBqHf3NIbVA2aAxj2+zTZCc/+zkqDjnhWFcKLU4hAH5j6mQrDcn7hzE5ic/nyILQOyO2hQVXqM82RFcIJPCkE=";
    private String vzhuoPublicKeyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAv90VMyNkiFn/BfWfKpMngLiaWFpb4O0vFAE0ecEGYIM8KZy+qfUiFhg/s0SuyUSFCffzZyapwI9CwTAHGNv6uPq5d2KSc6MVSws3/CsWz2oO7xtL1fTunCrBhv0RjYvWK/cqgQ2SuXPbIja8BROAhqNAbMYhruTzNQEh/isy+InCcFe+rEFXkKdjUA0Q38QTS/hY0HbDxQdVTKVaqGEK6AoMs6olKxFSJfuaU3qjbpljFjUfmkyM7fRebvhU4uboJsEtmt4TQq1mdAIbzQlUXrOzc7qC05zkknuxPvzikeOZ3GuiY5C1HvLDw7yULd87t7jS8gdnVKa4Mobn6KCS/wIDAQAB";
    private String url = "http://test.web.cass.grianchan.com/gateway/cass";
    private String APPID = "R6N57SSOAG04FLTRV1LBITJIEK";
    private DefaultCassPayClient client;

    @Before
    public void init() throws InvalidPrivateKeyException, InvalidPublicKeyException {
        this.client = new DefaultCassPayClient(this.url, this.APPID, this.privateKeyStr, this.vzhuoPublicKeyStr, "JSON", "RSA2");

    }

    @Test
    public void initClient() throws InvalidPrivateKeyException, InvalidPublicKeyException {
        new DefaultCassPayClient(this.url, this.APPID, this.privateKeyStr, this.vzhuoPublicKeyStr, "JSON", "RSA2");
    }

    @Test
    public void testGetBankBalance() throws Exception {
        GetBalanceRequest request = new GetBalanceRequest();
        request.setPayChannelK(GetBalanceRequest.BANK);
        GetBalanceResponse response = this.client.execute(request);

        System.out.println(response);
        assertEquals("10000", response.code);
        assertEquals("请求成功", response.message);
        assertEquals("", response.subCode);
        assertEquals("", response.subMsg);
        assertNotEquals("", response.bank.bankAccout);
        assertNotEquals("", response.bank.lockedAmt);
        assertNotEquals("", response.bank.canUseAmt);
        assertNotEquals("", response.bank.childFAbalance);
        assertNotEquals("", response.bank.mandatoryName);
        assertTrue(response.verify());
    }

    @Test
    public void testPayBankRemit() throws Exception {
        PayBankRemitRequest request = new PayBankRemitRequest();
        request.setPayChannelK(GetBalanceRequest.BANK);
        BankPayOrder[] orders = new BankPayOrder[]{
                new BankPayOrder("123456", "12345678910", "詹光", "1.00")
        };
        request.setOrders(orders);
        PayBankRemitResponse response = this.client.execute(request);
        ;

        System.out.println(response);
        assertEquals("10000", response.code);
        assertEquals("请求成功", response.message);
        assertEquals("", response.subCode);
        assertEquals("", response.subMsg);
        assertNotNull(response.rbUUID);
        assertTrue(response.verify());
    }

    @Test
    public void testGetOneRemitStatus() throws ResponseNotValidException {
        BankPayOrder bankPayOrder = new BankPayOrder("123456", "12345678910", "123光", "1.00");
        PayBankRemitResponse payResponse = this.client.execute((new PayBankRemitRequest()).setPayChannelK(GetBalanceRequest.BANK).setOrders(new BankPayOrder[]{
                bankPayOrder
        }));
        ;
        String rbUUID = payResponse.rbUUID;
        GetOneRemitStatusRequest request = new GetOneRemitStatusRequest();
        request.setRbUUID(rbUUID);
        GetOneRemitStatusResponse response = this.client.execute(request);
        assertEquals(rbUUID, response.rbUUID);
        RemitOrder remitOrder = response.remitOrders.get(0);
        assertNotNull(remitOrder);
        assertEquals(bankPayOrder.orderSN, remitOrder.orderSN);
    }

    @Test
    public void testGetOneOrderStatusByBank() throws Exception {
        BankPayOrder bankPayOrder = new BankPayOrder("123456", "12345678910", "詹光", "1.00");
        PayBankRemitResponse payResponse = this.client.execute((new PayBankRemitRequest()).setPayChannelK(GetBalanceRequest.BANK).setOrders(new BankPayOrder[]{
                bankPayOrder
        }));
        ;

        String rbUUID = payResponse.rbUUID;
        GetOneRemitStatusResponse response = this.client.execute(new GetOneRemitStatusRequest().setRbUUID(rbUUID));
        RemitOrder remitOrder = response.remitOrders.get(0);
        String orderUUID = remitOrder.orderUUID;
        GetOneOrderStatusRequest oneOrderStatusRequest = new GetOneOrderStatusRequest();
        oneOrderStatusRequest.setOrderUUID(orderUUID);
        GetOneOrderStatusResponse getOneOrderStatusResponse = this.client.execute(oneOrderStatusRequest);
        assertTrue(getOneOrderStatusResponse.verify());
        assertEquals(bankPayOrder.orderSN, getOneOrderStatusResponse.orderSN);
        assertEquals(rbUUID, getOneOrderStatusResponse.rbUUID);
    }

    @Test
    public void testGetOneOrderStatusByAliPay() throws Exception {
        AliPayOrder aliPayOrder = new AliPayOrder("123456", "13517210601", "詹光1", "1.00");
        PayAliRemitResponse payResponse = this.client.execute(
                (new PayAliRemitRequest())
                        .setPayChannelK(GetBalanceRequest.AliPay)
                        .setOrders(new AliPayOrder[]{
                                aliPayOrder
                        }));
        System.out.println(payResponse.raw);
        assertNotNull(payResponse.rbUUID);
        String rbUUID = payResponse.rbUUID;
        GetOneRemitStatusResponse response = this.client.execute(new GetOneRemitStatusRequest().setRbUUID(rbUUID));
        RemitOrder remitOrder = response.remitOrders.get(0);
        String orderUUID = remitOrder.orderUUID;
        GetOneOrderStatusRequest oneOrderStatusRequest = new GetOneOrderStatusRequest();
        oneOrderStatusRequest.setOrderUUID(orderUUID);
        GetOneOrderStatusResponse getOneOrderStatusResponse = this.client.execute(oneOrderStatusRequest);
        assertTrue(getOneOrderStatusResponse.verify());
        assertEquals(aliPayOrder.orderSN, getOneOrderStatusResponse.orderSN);
        assertEquals(rbUUID, getOneOrderStatusResponse.rbUUID);
    }
}
