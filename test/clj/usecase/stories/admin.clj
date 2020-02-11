(ns usecase.stories.admin
  "General process of administration of the system"
  (:require [clojure.test :refer [deftest is testing]]))

(deftest manage-users
  (testing "Admin can CRUD users"
    (is false))
  (testing "Non-admin CANNOT CRUD users"
    (is false))
  (testing "Admin reset user passwords"
    (is false)))

(deftest manage-articles
  (testing "Admin can bulk-add new articles"
    (is false))
  (testing "Admin can bulk-delete articles"
    (is false))
  (testing "Admin can see stats over all articles and users"
    (is false))
  (testing "admin can see articles underway by all users"
    (is false))
  (testing "Admin can make article assignments for users"
    (is false))
  (testing "Admin buckets"
    (is false "Admin can access buckets by user")
    (is false "Admin can access buckets by bucket-type")))

(deftest masquerade
  (testing "Admin can change user bucket assignments"
    (is false)))


(deftest timelines
  (testing "Compare timelines across articles"
    (is false)))
