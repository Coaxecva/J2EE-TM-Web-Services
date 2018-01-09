public interface BookQuote {
  public float getBookPrice
    (String isbn);
  
  public float getBulkBookPrice
    (String isbn, int quantity);

  public String getBookIsbn
    (String bookTitle);
}

/*
<portType name="BookQuote">
  <operation name="GetBookPrice">
    <input
    name="isbn"
    message="mh:GetBookPriceRequest"/>
    <output
    name="price"
    message="mh:GetBookPriceResponse"/>
  </operation>
  <operation name="GetBulkBookPrice">
    <input
    name="request"
    message="mh:GetBulkBookPriceRequest"/>
    <output name="prices"
    message="mh:GetBulkBookPriceResponse"/>
  </operation>
  <operation name="GetBookIsbn">
    <input
    name="title"
    message="mh:GetBookIsbnRequest"/>
    <output
    name="isbn"
    message="mh:GetBookIsbnResponse"/>
  </operation>
</portType>
*/
