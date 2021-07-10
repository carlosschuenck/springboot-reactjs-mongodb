print('###################### Start creating Contact database ######################')
db = db.getSiblingDB('contactdb')
db.createUser(
  {
    user: 'admin',
    pwd: 'admin',
    roles: [
      {
        role: 'readWrite',
        db: 'contactdb'
      }
    ]
  }
)
print('###################### End creating Contact database ######################')