package com.kayk.order_service_v1.order.template;

import com.kayk.order_service_v1.order.Orderitem;
import com.kayk.order_service_v1.order.dtos.OrderEventDTO;

public class EmailTemplate {

    public static String BuildHtlmTemplate(OrderEventDTO event) {
        StringBuilder itensHtml = new StringBuilder();

        for (Orderitem item : event.orderItem()) {
            itensHtml.append("""
                    <tr>
                        <td style="padding:12px; border-bottom:1px solid #eee; color:#333;">%s</td>
                        <td style="padding:12px; border-bottom:1px solid #eee; text-align:center; color:#333;">%d</td>
                        <td style="padding:12px; border-bottom:1px solid #eee; text-align:right; color:#333;">R$ %.2f</td>
                    </tr>
                    """.formatted(item.getProduct(), item.getQuantity(), item.getUnitPrice()));
        }

        return """
            <!DOCTYPE html>
            <html>
            <body style="margin:0; padding:0; background-color:#f4f4f7; font-family:Arial, sans-serif;">
                <table width="100%%" cellpadding="0" cellspacing="0" style="padding:30px 0;">
                    <tr>
                        <td align="center">
                            <table width="600" cellpadding="0" cellspacing="0" style="background:#ffffff; border-radius:8px; overflow:hidden; box-shadow:0 2px 8px rgba(0,0,0,0.08);">

                                <tr>
                                    <td style="background-color:#1F3864; padding:24px; text-align:center;">
                                        <h1 style="color:#ffffff; margin:0; font-size:20px; letter-spacing:1px;">ORDER SERVICE</h1>
                                    </td>
                                </tr>

                                <tr>
                                    <td style="padding:30px;">
                                        <h2 style="color:#222; margin-top:0;">Pedido Confirmado! 🎉</h2>
                                        <p style="color:#555; font-size:14px;">
                                            Olá <strong>%s</strong>, recebemos seu pedido <strong>#%s</strong> com sucesso.
                                        </p>

                                        <table width="100%%" cellpadding="0" cellspacing="0" style="margin-top:20px; border-collapse:collapse;">
                                            <tr style="background-color:#f4f4f7;">
                                                <td style="padding:10px; font-size:13px; color:#888; font-weight:bold;">PRODUTO</td>
                                                <td style="padding:10px; font-size:13px; color:#888; font-weight:bold; text-align:center;">QTD</td>
                                                <td style="padding:10px; font-size:13px; color:#888; font-weight:bold; text-align:right;">SUBTOTAL</td>
                                            </tr>
                                            %s
                                        </table>

                                        <table width="100%%" style="margin-top:20px;">
                                            <tr>
                                                <td style="text-align:right; font-size:16px; color:#222;">
                                                    <strong>Total: R$ %.2f</strong>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>

                                <tr>
                                    <td style="background-color:#f4f4f7; padding:20px; text-align:center;">
                                        <p style="color:#999; font-size:12px; margin:0;">
                                            Order Service &copy; 2026 — Este é um e-mail automático, não responda.
                                        </p>
                                    </td>
                                </tr>

                            </table>
                        </td>
                    </tr>
                </table>
            </body>
            </html>
            """.formatted(event.client(), event.id(), itensHtml.toString(), event.totalValue());
    }
}
