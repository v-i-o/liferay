package cz.actum.portlet.news;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import cz.actum.portlet.constants.NewsPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Andrey
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.version=3.0",
                "com.liferay.portlet.display-category=actum",
                "com.liferay.portlet.header-portlet-css=/css/main.css",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=NewsPortlet",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + NewsPortletKeys.NEWSPORTLET,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class NewsPortlet extends MVCPortlet {

    private String from = "not set";
    private String to = "not set";

    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
        renderRequest.setAttribute("from", from);
        renderRequest.setAttribute("to", to);
        super.render(renderRequest, renderResponse);
    }

    public void setStops(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException {
        from = ParamUtil.getString(actionRequest, "from");
        to = ParamUtil.getString(actionRequest, "to");
        parsePage(from, to);
    }

    private void parsePage(String from, String to) throws IOException {
        WebClient webClient = new WebClient(BrowserVersion.CHROME, false, null, 0);
        HtmlPage page = webClient.getPage("https://idos.idnes.cz/mladaboleslav/spojeni/vysledky/?f=" + from + "&fc=333003&t=" + to + "&tc=333003");
        List<HtmlDivision> elements = page.getByXPath("//div[contains(@class, 'box connection detail-box ca-collapsed')]");
    }

}