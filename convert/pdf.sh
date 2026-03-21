cd ../

pandoc README.md -o ~/Downloads/file.pdf \
  --pdf-engine=wkhtmltopdf \
  --highlight-style=tango \
  --css convert/hide-captions.css \
  --variable monofont="Courier New"