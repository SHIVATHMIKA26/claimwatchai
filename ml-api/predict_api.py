from flask import Flask, request, jsonify
import pandas as pd
import joblib

app = Flask(__name__)

# Load model
metadata = joblib.load("claimwatch_model.pkl")
model = metadata["model"]
features = metadata["features"]

@app.route("/predict", methods=["POST"])
def predict():
    try:
        data = request.json
        print("Received data:", data)

        # Convert to dataframe
        input_data = pd.DataFrame([{
            "months_as_customer": data["monthsAsCustomer"],
            "age": data["age"],
            "policy_deductable": data["policyDeductable"],
            "policy_annual_premium": data["policyAnnualPremium"],
            "umbrella_limit": data["umbrellaLimit"],
            "incident_severity": data["incidentSeverity"],
            "number_of_vehicles_involved": data["numberOfVehiclesInvolved"],
            "bodily_injuries": data["bodilyInjuries"],
            "total_claim_amount": data["totalClaimAmount"]
        }])

        prob = model.predict_proba(input_data)[0][1]

        if prob > 0.20:
            result = "FRAUD"
        else:
            result = "LEGIT"

        print("Fraud Probability:", prob)
        print("Prediction:", result)

        return jsonify({
            "prediction": result,
            "fraudProbability": float(prob)
        })

    except Exception as e:
        print("ERROR:", e)
        return jsonify({"error": str(e)}), 500


if __name__ == "__main__":
    app.run(port=5001)