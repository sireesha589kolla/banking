package com.staragile.Banking.Finance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MvcFinanceController {

    @Autowired
    private AccountService accountSvc;

    @RequestMapping("mvchello")
    public String doSomething() {
        return "Hello World!!!";
    }

    @RequestMapping("/getallaccount")
    public String getAllAccounts(HttpServletRequest req, HttpServletResponse res) {
        List<Account> accountList = accountSvc.getAccounts();
        req.getSession().setAttribute("accountList", accountList);
		return "account.jsp";
		
		/*model.addAttribute("accountList", accountList);
        return "account"; // Return the name of the JSP file (without the .jsp extension)*/
    }


    @RequestMapping("/createaccount")
    public String addAccount(HttpServletRequest req, HttpServletResponse res, @ModelAttribute("account") Account account) {
        // Create a new Account object and populate it with values from the request parameters
        Account newAccount = new Account();
        newAccount.setAccountId(req.getParameter("id"));
        newAccount.setCustomerName(req.getParameter("name"));
        newAccount.setCustomerAddress(req.getParameter("address"));
        newAccount.setContactNumber(req.getParameter("contact"));

        // Add the newAccount to the accountSvc
        accountSvc.addAccount(newAccount);

        return "redirect:/getallaccount";
    }


    @RequestMapping("/updateaccount")
    public String updateAccount(HttpServletRequest req, HttpServletResponse res, @ModelAttribute("account") Account account) {
        // Create a new Account object and populate it with values from the request parameters
        Account updatedAccount = new Account();
        updatedAccount.setAccountId(req.getParameter("id"));
        updatedAccount.setCustomerName(req.getParameter("name"));
        updatedAccount.setCustomerAddress(req.getParameter("address"));
        updatedAccount.setContactNumber(req.getParameter("contact"));

        // Update the account using the accountSvc
        accountSvc.updateAccount(updatedAccount.getAccountId(), updatedAccount);

        return "redirect:/getallaccount";
    }


    @RequestMapping("/viewaccount/{id}")
    public ModelAndView viewAccount(@PathVariable String id) {
        Optional<Account> account = accountSvc.getAccount(id);
        ModelAndView modelAndView = new ModelAndView("viewaccount");
        modelAndView.addObject("account", account.orElse(new Account()));
        return modelAndView;
    }


    @RequestMapping("/deleteaccount/{id}")
    public String deleteAccount(@PathVariable String id) {
        accountSvc.deleteAccount(id);
        return "redirect:/getallaccount";
    }

    @GetMapping("/editaccount/{id}")
    public ModelAndView editAccount(@PathVariable String id) {
        Optional<Account> account = accountSvc.getAccount(id);
        ModelAndView modelAndView = new ModelAndView("editaccount");
        modelAndView.addObject("account", account.orElse(new Account()));
        return modelAndView;
    }
}
