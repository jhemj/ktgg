mvn package -B -Dmaven.test.skip=true

docker build -t yeseong33/consultation:241010 .     
docker push yeseong33/consultation:241010 

kubectl delete deployment consultation
kubectl apply -f kubernetes/deployment.yaml
kubectl apply -f kubernetes/service.yaml

cd ..

cd kubernetes
kubectl apply -f template.yml