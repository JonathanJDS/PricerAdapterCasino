echo "stopping PricerCheckLinkedAndPrint"
net stop PricerCheckLinkedAndPrint
echo "service PricerCheckLinkedAndPrint stopped"
echo "delete services"
sc delete PricerCheckLinkedAndPrint

