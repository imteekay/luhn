(ns luhn.core-test
  (:require [clojure.test :refer :all]
            [luhn.core :refer :all]))

(deftest remove-hyphen-test
  (testing "Remove hyphen from string"
    (is (= (remove-hyphen "123-123") "123123"))
    (is (= (remove-hyphen "123123") "123123"))
    (is (= (remove-hyphen "") ""))))

(deftest seq-char->int-test
  (testing "Transform sequence char into an integer"
    (is (= (seq-char->int \6) 6))
    (is (= (seq-char->int \2) 2))))

(deftest string->ints-test
  (testing "Transform a string into a vector of integers"
    (is (= (string->ints " -123-453-349  ") '(1 2 3 4 5 3 3 4 9)))))

(deftest double-two-by-two-test
  (testing "Double numbers two by two from the penultimate element"
    (is (= (double-two-by-two '(1 2 3 4)) '(2 2 6 4)))
    (is (= (double-two-by-two '(1 2 3 4 5)) '(1 4 3 8 5)))
    (is (= (double-two-by-two '(5 6 7 8)) '(10 6 14 8)))))

(deftest digits-test
  (testing "Make an integer into a list of single digits"
    (is (= (digits 1) '(1)))
    (is (= (digits 10) '(1 0)))
    (is (= (digits 92) '(9 2)))))

(deftest sum-all-digits-test
  (testing "Sum all digits in the list"
    (is (= (sum-all-digits '(10 6 14 8)) 20))
    (is (= (sum-all-digits '(2 6 1 8)) 17))
    (is (= (sum-all-digits '(10 61 24 81)) 23))))

(deftest luhn-check?-test
  (testing "Check luhn logic"
    (is (= (luhn-check? 10) true))
    (is (= (luhn-check? 20) true))
    (is (= (luhn-check? 22) false))
    (is (= (luhn-check? 33) false))))

(deftest luhn?-test
  (testing "Test if the credit card number passes the Luhn check"
    (is (= (luhn? "5678") true))
    (is (= (luhn? "6789") false))
    (is (= (luhn? "3566-0020-2036-0505") true))
    (is (= (luhn? "5555-5555-5555-4444") true))
    (is (= (luhn? "5105-1051-0510-5100") true))
    (is (= (luhn? "4111-1111-1111-1111") true))
    (is (= (luhn? "3773-141-7670-4310") true))
    (is (= (luhn? "3793-8559-4311-738") true))))
