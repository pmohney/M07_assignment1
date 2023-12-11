// 12/10/23
// Pierce Mohney
// SDEV 200
// M07 assignment 1
// This program will take user inputted salary and marital status and then output their loan amount, 
// annual interest rate, number of years for the loan term, the monthly payment amount, and the total payment amount. 

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@WebServlet("/LoanCalculator/LoanServlet")
public class LoanServlet extends HttpServlet {
    private static class Loan {
        private double annualInterestRate;
        private int numberOfYears;
        private double loanAmount;
        private Date loanDate;

        public Loan() {
            this(2.5, 1, 1000);
        }

        public Loan(double annualInterestRate, int numberOfYears, double loanAmount) {
            this.annualInterestRate = annualInterestRate;
            this.numberOfYears = numberOfYears;
            this.loanAmount = loanAmount;
            this.loanDate = new Date();
        }

        public double getAnnualInterestRate() {
            return annualInterestRate;
        }

        public void setAnnualInterestRate(double annualInterestRate) {
            this.annualInterestRate = annualInterestRate;
        }

        public int getNumberOfYears() {
            return numberOfYears;
        }

        public void setNumberOfYears(int numberOfYears) {
            this.numberOfYears = numberOfYears;
        }

        public double getLoanAmount() {
            return loanAmount;
        }

        public void setLoanAmount(double loanAmount) {
            this.loanAmount = loanAmount;
        }

        public double getMonthlyPayment() {
            double monthlyInterestRate = annualInterestRate / 1200;
            double monthlyPayment = loanAmount * monthlyInterestRate /
                    (1 - (1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12)));
            return monthlyPayment;
        }

        public double getTotalPayment() {
            double totalPayment = getMonthlyPayment() * numberOfYears * 12;
            return totalPayment;
        }

        public Date getLoanDate() {
            return loanDate;
        }
    }
//10.2 Loan class

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        double annualInterestRate = Double.parseDouble(request.getParameter("annualInterestRate"));
        int numberOfYears = Integer.parseInt(request.getParameter("numberOfYears"));
        double loanAmount = Double.parseDouble(request.getParameter("loanAmount"));
        String filingStatus = request.getParameter("filingStatus");
//Obtains user input

        Loan userLoan = new Loan(annualInterestRate, numberOfYears, loanAmount);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Loan Calculation Result</title></head><body>");
        out.println("<h2>Loan Calculation Result:</h2>");
        out.println("<p>Income: $" + userLoan.getLoanAmount() + "</p>");
        out.println("<p>Filing Status: " + filingStatus + "</p>");
        out.println("<p>Annual Interest Rate: " + userLoan.getAnnualInterestRate() + "</p>");
        out.println("<p>Number of Years: " + userLoan.getNumberOfYears() + "</p>");
        out.println("<p>Monthly Payment: $" + userLoan.getMonthlyPayment() + "</p>");
        out.println("<p>Total Payment: $" + userLoan.getTotalPayment() + "</p>");
//Methods for calculating and displaying results 

        request.setAttribute("monthlyPayment", String.valueOf(userLoan.getMonthlyPayment()));
        request.setAttribute("totalPayment", String.valueOf(userLoan.getTotalPayment()));
        request.getRequestDispatcher("loanForm.jsp").forward(request, response);
//JSP parameters

        out.println("</body></html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.html");
    }
}
