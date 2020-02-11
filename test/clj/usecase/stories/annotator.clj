(ns stories.annotator
  "General process by which an annotator embarks on their work"
  (:require [clojure.test :refer [deftest is testing]]))

(deftest annotator-workflow
  (testing "Annotator can login"
    (is false "Annotator logs in")
    (is false "Annot assigned correct privileges"))
  (testing "Annotator can view available articles requiring annotation"
    (is false "sees all available articles")
    (is false "Sees articles underway")
    (is false "Sees articles previously completed"))
  (testing "Annotator can load a selected article"
    (is false "Displays article")
    (is false "Can annotate with events"))
  (testing "annotator can submit complete tagging"
    (is false "Sees stats on completed article (num events, etc)")
    (is false "Submission adds to database")
    (is false "Annotator receives confirmation of successful submission")
    (is false "Annotator goes back to select screen")))

(deftest tags-buckets
  (testing "Annotator can assign tags to articles"
    (is false "Incomplete articles can be assigned")
    (is false "Articles can be reassigned different tags")))

(deftest annotation
  (testing "Time annotations"
    (is false))
  (testing "Entity assignment"
    (is false))
  (testing "Coreference resolution"
    (is false)))

(deftest timeline-generation
  (testing "any events produce a timeline"
    (is false))
  (testing "timeline interactively can link to events"
    (is false))
  (testing "timeline zooming"
    (is false))
  (testing "parallel timelines"
    (is false))
  (testing "fuzzy event relations: time vaguaries"
    (is false)))

(deftest score-articles
  (testing "Annotator can go into score-mode"
    (is false))
  (testing "Annotator can provide alternate annotations for articles"
    (is false))
  (testing "Annotator can mark errors in an article"
    (is false)))


