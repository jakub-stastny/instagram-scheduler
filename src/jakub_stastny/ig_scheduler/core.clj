(ns jakub-stastny.ig-scheduler.core
  (:require [clj-http.client :as client]
            [cheshire.core :as json]))

(defn verify-env [var-name]
  (or (System/getenv var-name)
      (throw (ex-info (str "Missing ENV variable " var-name) {}))))

(defn upload-photo [access-token account-id image-url caption]
  (let [url (str "https://graph.instagram.com/v13.0/" account-id "/media")
        params {:access_token access-token
                :image_url image-url
                :caption caption}
        response (client/post url {:form-params params :content-type :json})]
    (println "Request URL:" url)
    (println "Request Params:" params)
    (if (= 200 (:status response))
      (println "Photo uploaded successfully")
      (do
        (println "Failed to upload photo")
        (println "Status Code:" (:status response))
        (println "Response Body:" (:body response))))))

(defn -main [& args]
  (let [ig-access-token (verify-env "IG_ACCESS_TOKEN")
        ig-account-id (verify-env "IG_ACCOUNT_ID")
        default-caption "#calupoh #perrolobo #perrolobomexicano #perrolobodeméxico #wolfdog #wolfdogpuppy #wolfdogcommunity"
        image-path (first args)]
    (println "~ Uploading" image-path)
    (upload-photo ig-access-token ig-account-id image-path default-caption)))
